import React, {Component} from "react";
import "../style/UploadForm.css";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class User extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            username: "",
            lastname: "",
            firstname: "",
            address: "",
            phoneNumber: "",
            rank: "",
            email: "",
            userid: this.props.match.params.userid
        }
    }



    validateForm() {
        return this.state.email.length > 0 && this.state.lastname.length > 0 && this.state.address.length > 0 &&
            this.state.phoneNumber.length > 0 && this.state.firstname.length > 0 && this.state.rank.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value

        });
    }

    handleSubmit = event => {
        event.preventDefault();
        var form = document.forms.namedItem("mapUpload");
        var formdata = new FormData(form);
        console.log("test");
        this.Auth.fetchForm('http://localhost:8080/map/upload',{
            method: 'POST',
            body: formdata
        }).then(res => {
            if(res.status === 201)
                this.props.history.replace('/maps');
        }).catch((err) => {
            alert(err);
        })
    }

    componentWillMount(){
        if(!this.Auth.loggedIn()){
            alert('Please login')
            this.props.history.replace('/login');
        }
        this.Auth.fetch(this.Auth.domain + '/Accounts/' + this.state.userid + '?access_token=' + this.Auth.getToken(),{
            method: 'GET'
        }).then(res => {
            console.log(res);
        })
    }


    render() {
        return (
            <div className="Upload">
                <form onSubmit={this.handleSubmit}>
                    <FormGroup controlId="lastname" bsSize="large">
                        <ControlLabel>LastName</ControlLabel>
                        <FormControl
                            name="lastname"
                            id="lastname"
                            maxLength="255"
                            autoFocus
                            type="text"
                            value={this.state.lastname}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="firstname" bsSize="large">
                        <ControlLabel>FirstName</ControlLabel>
                        <FormControl
                            name="firstname"
                            maxLength="255"
                            id="firstname"
                            autoFocus
                            type="text"
                            value={this.state.firstname}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="address" bsSize="large">
                        <ControlLabel>Address</ControlLabel>
                        <FormControl
                            name="address"
                            id="address"
                            value={this.state.address}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <FormGroup controlId="phoneNumber" bsSize="large">
                        <ControlLabel>phoneNumber</ControlLabel>
                        <FormControl
                            name="phoneNumber"
                            id="phoneNumber"
                            value={this.state.phoneNumber}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <FormGroup controlId="rank" bsSize="large">
                    <ControlLabel>Rank</ControlLabel>
                    <FormControl
                        name="rank"
                        id="rank"
                        value={this.state.rank}
                        onChange={this.handleChange}
                        type="number"
                    />
                </FormGroup>
                    <Button
                        block
                        bsSize="large"
                        disabled={!this.validateForm()}
                        type="submit"
                    >
                        Upload
                    </Button>
                </form>
            </div>

        );
    }
}
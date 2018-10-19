import React, {Component} from "react";
import "../style/UploadForm.css";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class User extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            loaded: "",
            username: "",
            lastname: "",
            firstname: "",
            address: "",
            phoneNumber: "",
            rank: "",
            email: "",
            userid: this.props.match.params.userid,
            items: []
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
        this.sendChange();
        console.log("test");
        this.sendChange();
    }

    sendChange() {
        this.Auth.fetchForm(this.Auth.domain + '/Accounts/' + this.state.userid + '?access_token=' + this.Auth.getToken(),{
            method: 'PATCH',
            body: JSON.stringify(
                this.state.username,
                this.state.rank,
                this.state.phoneNumber,
                this.state.lastname,
                this.state.firstname,
                this.state.email,
                this.state.address
            )
        }).then(res => {
            if(res.status === 201){
                this.props.history.replace('/users');
                alert("Succesful change");
            }
        }).catch((err) => {
            alert(err);
        })
    }

    componentWillMount(){
        if(!this.Auth.loggedIn()){
            alert('Please login')
            this.props.history.replace('/login');
        }
        console.log(this.state.userid);
    }


    render() {
        this.Auth.fetch(this.Auth.domain + '/Accounts?access_token=' + this.Auth.getToken(),{
            method: 'GET'
        }).then(items => {
            this.setState({items});
            console.log(items);
            this.state.items.map(item => {
                if(item.id == this.state.userid){
                    this.state.username = item.username;
                    this.state.lastname = item.lastname;
                    this.state.rank = item.rank;
                    this.state.phoneNumber = item.phoneNumber;
                    this.state.firstname = item.firstname;
                    this.state.email = item.email;
                    this.state.address = item.address;
                    this.state.loaded = true;
                }
            })
        });

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
                        <FormGroup controlId="email" bsSize="large">
                            <ControlLabel>E-mail</ControlLabel>
                            <FormControl
                                name="email"
                                id="email"
                                value={this.state.email}
                                onChange={this.handleChange}
                                type="email"
                            />
                        </FormGroup>
                        <Button
                            block
                            bsSize="large"
                            disabled={!this.validateForm()}
                            type="submit"
                        >
                            Change
                        </Button>
                    </form>
                </div>
            );
        }
    }
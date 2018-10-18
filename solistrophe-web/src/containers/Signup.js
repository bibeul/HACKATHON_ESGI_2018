import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../style/Signup.css";
import Auth from './Auth';

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.Auth = new Auth();
        this.state = {
            username: "",
            email: "",
            password: "",
            lastname: "",
            firstname: "",
            phoneNumber: "",
            address: ""
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0 && this.state.username.length > 0
            && this.state.firstname.length > 0 && this.state.lastname.length > 0 && this.state.phoneNumber.length > 0
            && this.state.address.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();

        this.Auth.register(this.state.username, this.state.email, this.state.password, this.state.firstname,
            this.state.lastname, this.state.phoneNumber, this.state.address)
            .then(res =>{
                this.props.history.replace('/');
            })
            .catch(err =>{
                alert(err);
            })
    }

    componentWillMount(){
        if(this.Auth.loggedIn()){
            alert('already logged in')
            this.props.history.replace('/');
        }

    }


    render() {
        return (
            <div className="Signup">
                <form onSubmit={this.handleSubmit}>
                    <FormGroup controlId="username" bsSize="large">
                        <ControlLabel>Username</ControlLabel>
                        <FormControl
                            autoFocus
                            type="text"
                            value={this.state.username}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="email" bsSize="large">
                        <ControlLabel>Email</ControlLabel>
                        <FormControl
                            autoFocus
                            type="email"
                            value={this.state.email}
                            onChange={this.handleChange}
                        />
                    </FormGroup>
                    <FormGroup controlId="password" bsSize="large">
                        <ControlLabel>Password</ControlLabel>
                        <FormControl
                            value={this.state.password}
                            onChange={this.handleChange}
                            type="password"
                        />
                    </FormGroup>
                    <FormGroup controlId="lastname" bsSize="large">
                        <ControlLabel>LastName</ControlLabel>
                        <FormControl
                            value={this.state.lastname}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <FormGroup controlId="firstname" bsSize="large">
                        <ControlLabel>FirstName</ControlLabel>
                        <FormControl
                            value={this.state.firstname}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <FormGroup controlId="phoneNumber" bsSize="large">
                        <ControlLabel>Phone Number</ControlLabel>
                        <FormControl
                            value={this.state.phoneNumber}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <FormGroup controlId="address" bsSize="large">
                        <ControlLabel>Address</ControlLabel>
                        <FormControl
                            value={this.state.address}
                            onChange={this.handleChange}
                            type="text"
                        />
                    </FormGroup>
                    <Button
                        block
                        bsSize="large"
                        disabled={!this.validateForm()}
                        type="submit"
                    >
                        Register
                    </Button>
                </form>
            </div>
        );
    }
}
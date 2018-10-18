import React, {Component} from "react";
import "../style/Users.css";
import { Link } from "react-router-dom";
import { Nav, Navbar, NavItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import Auth from './Auth';

export default class Users extends Component {
    constructor(props){
        super(props);
        this.Auth = new Auth();
        this.state = {
            items: []

        }
    }

    componentWillMount(){
        this.Auth.fetch(this.Auth.domain + '/Accounts?access_token=' + this.Auth.getToken(),{
            method: 'GET'
        })
            .then(items => this.setState({items}));
    }

    handledelete = (mapname) => (event) => {
        event.preventDefault();

        this.Auth.fetchDelete('http://localhost:8080/map/delete/'+mapname,{
            method: 'DELETE'
        })
            .then(() => {})
            .catch((err)=>{
                alert(err);
            });
    }

    handlesubmit = (userid) => (event) => {
        event.preventDefault();
        this.Auth.banUser(userid);
    }

    render(){
        return (
                <div class="Users">
                {this.state.items.map(user =>
                    <div class="User" id={user.id}>
                        <p>{user.firstname}</p>
                        <p>{user.lastname}</p>
                        <div class="allButton">
                            <LinkContainer to={"/user/"+user.id}><button type="submit">Edit user</button></LinkContainer>
                            <button type="submit" onClick={this.handlesubmit(user.id)}>Ban User</button>
                        </div>
                    </div>
                )}
                </div>
        );
    }
}
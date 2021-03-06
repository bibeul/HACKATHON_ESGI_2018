import React, { Component, Fragment } from "react";
import { Link } from "react-router-dom";
import { Nav, Navbar, NavItem } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import Routes from "./Routes";
import "./App.css";
import Auth from "./containers/Auth";

class App extends Component {

    constructor(props) {
        super(props);
        this.Auth = new Auth();
        this.state = {
            isLogged: false,
            isAdmin: false
        }
    }

    userHasAuthenticated = authenticated => {
        this.setState({ isLogged: authenticated });
    }

    render() {
        const childProps = {
            isLogged: this.state.isAuthenticated,
            isAdmin: this.state.isAdmin,
            userHasAuthenticated: this.userHasAuthenticated
        };
        return (

            <div className="App container">
                <Navbar fluid collapseOnSelect>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <Link to="/" className="GoHome"></Link>
                        </Navbar.Brand>
                        <Navbar.Toggle />
                    </Navbar.Header>
                    <Navbar.Collapse>
                        <Nav pullRight>

                            {this.state.isLogged
                                ?<Fragment>
                                <LinkContainer to="/login">
                                    <NavItem>Logout</NavItem>
                                </LinkContainer>
                                <LinkContainer to="/sinisters">
                                    <NavItem>Sinisters</NavItem>
                                </LinkContainer>
                                <LinkContainer to="/users">
                                    <NavItem>Users</NavItem>
                                </LinkContainer>
                                </Fragment>
                                : <Fragment>
                                    <LinkContainer to="/register">
                                        <NavItem>Signup</NavItem>
                                    </LinkContainer>
                                    <LinkContainer to="/login">
                                        <NavItem>Login</NavItem>
                                    </LinkContainer>
                                </Fragment>
                            }

                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                <Routes childProps={childProps} />
            </div>
        );
    }



}

export default App;

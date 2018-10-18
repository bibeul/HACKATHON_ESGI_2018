import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./containers/Home";
import Users from "./containers/Users"
import NotFound from "./containers/NotFound";
import Login from "./containers/Login";
import Sinisters from "./containers/Sinisters";
import Signup from "./containers/Signup";
import UserProfile from "./containers/User"
import AppliedRoute from "./components/AppliedRoute";



export default ({ childProps }) =>
    <Switch>
        <AppliedRoute path="/" exact component={Home} props={childProps} />
        <AppliedRoute path="/login" exact component={Login} props={childProps} />
        <Route path="/users" exact component={Users} />
        <Route path="/sinisters" exact component={Sinisters} />
        <Route path="/register" exact component={Signup} />
        <Route path="/user/:userid" component={UserProfile}/>
        <Route component={NotFound} />
    </Switch>;

/*export default () =>
    <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/login" exact component={Login} />
        <Route path="/maps" exact component={Maps} />
        <Route path="/plugins" exact component={Plugins} />
        <Route path="/register" exact component={Signup} />
        <Route path="/uploadMap" exact component={UploadMap}/>
        <Route path="/uploadPlugin" exact component={UploadPlugin}/>
        <Route component={NotFound} />
    </Switch>;*/

import React from 'react'

import {Route, Switch, Redirect, withRouter} from 'react-router-dom'
import HomeComponent from "./HomeComponent";
import BookingComponent from "./BookingComponent";
import NavbarComponent from "./NavbarComponent";

function Main(){

    return(
        <div className="flex">
            <NavbarComponent/>
            <div className="flex-row w-full">
                <Switch>
                    <Route exact path='/' component={HomeComponent}/>
                    <Route exact path='/roomSelection' component={BookingComponent}/>
                </Switch>
            </div>
        </div>
    );
}

export default withRouter(Main);
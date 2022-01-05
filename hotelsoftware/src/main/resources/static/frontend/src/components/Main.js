import React from 'react'
import {Route, Switch, withRouter} from 'react-router-dom'
import '../style.css';

import HomeComponent from "./HomeComponent";
import NavbarComponent from "./NavbarComponent";
import BookingComponent from "./BookingComponent";

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
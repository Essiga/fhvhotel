import React from 'react'
import {Redirect, Route, Switch, withRouter} from 'react-router-dom'
import '../style.css';
import mountain from '../images/mountain.webp'

import HomeComponent from "./HomeComponent";
import NavbarComponent from "./NavbarComponent";
import BookingComponent from "./BookingComponent";

function Main(){

    return(
        <div className="flex bg-gray-100 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${mountain})`}}>
            <NavbarComponent/>
            <div className="flex w-full">
                <Redirect exact from="/index.html" to="/"/>
                <Switch>
                    <Route exact path='/' component={HomeComponent}/>
                    <Route exact path='/createBooking' component={BookingComponent}/>
                </Switch>
            </div>
        </div>
    );
}

export default withRouter(Main);
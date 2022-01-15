import React from 'react'
import {Route, Switch, withRouter} from 'react-router-dom'
import '../style.css';
import vacation from '../images/vacation.jpg'

import HomeComponent from "./HomeComponent";
import NavbarComponent from "./NavbarComponent";
import BookingComponent from "./BookingComponent";

function Main(){

    return(
        <div className="flex bg-gray-100 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${vacation})`}}>
            <NavbarComponent/>
            <div className="flex w-full">
                <div className="w-full">
                    <div className="h-full bg-white">
                        <Switch>
                            <Route exact path='/' component={HomeComponent}/>
                            <Route exact path='/createBooking' component={BookingComponent}/>
                        </Switch>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default withRouter(Main);
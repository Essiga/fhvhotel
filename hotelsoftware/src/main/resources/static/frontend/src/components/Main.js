import React from 'react'
import {Route, Switch, withRouter} from 'react-router-dom'
import '../style.css';

import HomeComponent from "./HomeComponent";
import NavbarComponent from "./NavbarComponent";
import RoomComponent from "./RoomComponent";

function Main(){

    return(
        <div className="flex bg-gray-100">
            <NavbarComponent/>
            <div className="flex w-full">
                <div className="w-full bg-white m-40 p-16 rounded border border-gray-400">

                    <Switch>
                        <Route exact path='/' component={HomeComponent}/>
                        <Route exact path='/allRooms' component={RoomComponent}/>
                    </Switch>
                </div>
            </div>
        </div>
    );
}

export default withRouter(Main);
import React from 'react'
import {Link} from "react-router-dom";

class HomeComponent extends React.Component{
    render()
    {
        return (
            <div>
                Welcome to [Placeholder] Hotel
                <br/>
                <Link to="/roomSelection">
                    <button className="bg-blue-400">Create Booking</button>
                </Link>
            </div>
        );
    }
}

export default HomeComponent;
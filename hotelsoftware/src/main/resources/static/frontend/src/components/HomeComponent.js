import React from 'react'

import {Link} from "react-router-dom";

class HomeComponent extends React.Component{
    render()
    {
        return (
            <div>
                <h1 className="pl-3 font-extrabold text-4xl text-blue-500 text-center">Welcome to Alp's Hotel</h1>

                <div className="mt-8 text-center">
                    <Link to="/createBooking">
                        <button className="w-1/4 p-2 rounded-lg border-2 border-opacity-75 self-center text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-2xl text-black">
                            Create Booking
                        </button>
                    </Link>
                </div>

            </div>
        );
    }
}

export default HomeComponent;
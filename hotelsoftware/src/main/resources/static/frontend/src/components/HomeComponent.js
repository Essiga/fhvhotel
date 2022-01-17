import React from 'react'
import hotel from '../images/hotel.png'
import {Link} from "react-router-dom";

class HomeComponent extends React.Component{
    render()
    {
        return (
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${hotel})`}}>
                <h1 className="mt-60 mb-10 font-serif font-extrabold text-4xl text-blue-200 text-center">Welcome to Alp's Hotel</h1>
                <div className="mt-8 text-center">
                    <Link to="/createBooking">
                        <button className="w-1/4 p-2 rounded-lg border-2 border-blue-200 self-center text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-2xl text-black opacity-95">
                            Create Booking
                        </button>
                    </Link>
                </div>

            </div>
        );
    }
}

export default HomeComponent;
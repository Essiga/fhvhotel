import React from 'react'
import {Link} from "react-router-dom";


class NavbarComponent extends React.Component{
    render()
    {
        return (
                <aside className="h-screen sticky top-0 w-1/6">
                    <nav className="flex-row h-screen w-full bg-green-500">
                        <div>
                            <Link to="/"> <span className="bg-green-500 block px-6 py-16 font-bold text-4xl text-white">Home</span> </Link>
                        </div>

                        <div>
                            <Link to="/createBooking"> <span className="bg-green-500 block px-6 py-8 font-bold text-4xl text-white">Create Booking</span> </Link>
                        </div>

                        <div>
                            <Link to="/allRooms"> <span className="bg-green-500 block px-6 py-8 font-bold text-4xl text-white">Room Overview</span> </Link>
                        </div>
                    </nav>
                </aside>
        );
    }
}

export default NavbarComponent;
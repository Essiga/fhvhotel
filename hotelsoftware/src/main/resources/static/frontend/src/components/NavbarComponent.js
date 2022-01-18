import React from 'react'
import {Link} from "react-router-dom";


class NavbarComponent extends React.Component{
    render()
    {
        return (
            <aside className="h-screen sticky top-0 w-1/6">
                <nav className="flex-row h-screen w-full bg-green-500 border-r-2 border-green-400 bg-opacity-70">
                    <div>
                        <Link to="/"> <span className="block px-6 py-16 font-semibold text-4xl text-gray-50 text-center">Home</span> </Link>
                    </div>

                    <div>
                        <Link to="/createBooking"> <span className="block px-6 py-8 font-semibold text-4xl text-gray-50 text-center">Create Booking</span> </Link>
                    </div>
                </nav>
            </aside>

        );
    }
}

export default NavbarComponent;
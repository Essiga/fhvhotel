import React from 'react'
import {Link} from "react-router-dom";


class NavbarComponent extends React.Component{
    render()
    {
        return (
            <aside className="h-screen sticky top-0 w-1/6">
                <nav className="flex-row h-screen w-full bg-green-500 border-2 border-green-400 opacity-80">
                    <div>
                        <Link to="/"> <span className="block px-6 py-16 font-bold text-4xl text-white text-center">Home</span> </Link>
                    </div>

                    <div>
                        <Link to="/createBooking"> <span className="block px-6 py-8 font-bold text-4xl text-white text-center">Create Booking</span> </Link>
                    </div>
                </nav>
            </aside>

        );
    }
}

export default NavbarComponent;
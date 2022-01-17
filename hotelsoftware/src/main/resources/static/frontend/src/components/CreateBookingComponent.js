import React from 'react'
import {Link} from "react-router-dom";
import lobby from "../images/lobby.jpg";

class CreateBookingComponent extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {guestId: ""};
    }

    componentDidMount() {

        const guestData = {
            gname: this.props.gname,
            voucher: this.props.voucher,
            firstName: this.props.firstName,
            lastName: this.props.lastName,
            streetAddress: this.props.streetAdr,
            zip: this.props.zip,
            city: this.props.city,
            country: this.props.country,
            phoneNumber: this.props.phone,
            email: this.props.email,
        };

        fetch("http://localhost:8080/rest/booking/createGuest",
            {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(guestData)
            })
            .then(res => res.json())
            .then(guestId => {
                this.setState({guestId: guestId})

                const bookingData =
                    {
                        guestId: this.state.guestId,
                        singleRoomCount: this.props.singleRoomCount,
                        doubleRoomCount: this.props.doubleRoomCount,
                        superiorRoomCount: this.props.superiorRoomCount,
                        checkInDate: this.props.checkInDate,
                        checkOutDate: this.props.checkOutDate
                    };

                fetch("http://localhost:8080/rest/booking/createBooking",
                    {
                        method: 'POST',
                        headers: {'content-type': 'application/json'},
                        body: JSON.stringify(bookingData)
                    })
            });
    }

    render()
    {
        return (
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${lobby})`}}>
                <h1 className="mt-60 mb-10 font-serif font-extrabold text-4xl text-blue-600 text-center">
                    We are looking forward to welcoming you
                </h1>

                <div className="mt-8 text-center">
                    <Link to="/">
                        <button className="w-1/4 p-2 rounded-lg border-2 border-opacity-75 self-center text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-2xl text-black">
                            Ok
                        </button>
                    </Link>
                </div>
            </div>
        );
    }
}

export default CreateBookingComponent;
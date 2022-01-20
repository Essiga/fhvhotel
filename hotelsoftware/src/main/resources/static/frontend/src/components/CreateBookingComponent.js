import React from 'react'
import {Link} from "react-router-dom";
import lobby from "../images/lobby.jpg";
import {BookingRestControllerApi} from "./api/src";

class CreateBookingComponent extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {guestId: ""};
    }

    componentDidMount() {
        const bookingRestControllerApi = new BookingRestControllerApi();
        const guestData = {
            // gname: this.props.gname,
            id: null,
            guestId: null,
            voucher: this.props.voucher,
            firstName: this.props.firstName,
            lastName: this.props.lastName,
            streetAddress: this.props.streetAdr,
            zip: this.props.zip,
            city: this.props.city,
            country: this.props.country,
            phoneNumber: this.props.phone,
            email: this.props.email,
            guestType: null,
            companyName: null,
            agencyName: null,
        };

        bookingRestControllerApi.createGuest(guestData, (error, data, response) =>{
            this.setState({guestId: data.guestId})
            console.log(data.guestId);
            const bookingData =
                {
                    guestId: this.state.guestId,
                    singleRoomCount: this.props.singleRoomCount,
                    doubleRoomCount: this.props.doubleRoomCount,
                    superiorRoomCount: this.props.superiorRoomCount,
                    checkInDate: this.props.checkInDate,
                    checkOutDate: this.props.checkOutDate
                };

            bookingRestControllerApi.createBooking(bookingData, () => {})

        })

    }

    render()
    {
        return (
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${lobby})`}}>

                <div className="p-1 mt-60 mb-10 bg-black bg-opacity-50 text-center">
                    <h1 className="font-serif font-extrabold text-4xl text-gray-50 text-center">We are looking forward to welcoming you</h1>
                </div>

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
import React from 'react';
import bed from "../images/bed.jpg";

class BookingSummaryComponent extends React.Component {

    constructor(props) {
        super(props);

        this.backBookingSummary = this.backBookingSummary.bind(this);
        this.createBooking = this.createBooking.bind(this);
    }

    backBookingSummary()
    {
        this.props.onBackBookingSummary();
    }

    createBooking()
    {
        this.props.onCreateBooking();
    }

    render() {
        return (
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover opacity-80" style={{backgroundImage: `url(${bed})`}}>

                <div className="py-4 h-1/12 mb-10 border-4 border-blue-200 rounded bg-gray-100 opacity-95">
                    <div className="w-full px-4 mb-8">
                        <div className="h-2px w-full bg-gray-400 relative">
                            <div className="ml-32 absolute top-1/2 left-0 h-1 bg-blue-400 w-2/3">
                                <div className="w-3 h-3 bg-blue-800 rounded-full absolute right-0 top-1/2 transform translate-x-1/2 -translate-y-1/2"></div>
                            </div>
                        </div>
                        <div className="ml-32 mt-3 pt-2 relative hidden sm:block">
                            <div className="absolute left-0"><span
                                className="inline-block transform -translate-x-1/2 text-m font-bold text-blue-400">Stay</span>
                            </div>
                            <div className="absolute left-1/3 pl-8"><span
                                className="inline-block transform -translate-x-1/2 text-m font-bold text-blue-400">Personal Data</span>
                            </div>
                            <div className="absolute left-2/3 pl-20"><span
                                className="inline-block transform -translate-x-1/2 text-m font-bold text-blue-400">Summary</span>
                            </div>
                        </div>
                    </div>
                </div>

                <form>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-4 font-semibold">Group/Company</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly type="text" id="gname" className="p-1 w-4/6"
                                value={this.props.gname}/>
                                <label htmlFor="gname" className="p-1 block text-sm text-gray-500">Name</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="voucher" className="p-1 w-2/6"
                                value={this.props.voucher}/>
                                <label htmlFor="voucher" className="p-1 block text-sm text-gray-500">Voucher Code</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-4 font-semibold">Full Name</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="firstName" className="p-1 w-4/6"
                                value={this.props.firstName}/>
                                <label htmlFor="firstName" className="p-1 block text-sm text-gray-500">First Name</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="lastName" className="p-1 w-4/6"
                                value={this.props.lastName}/>
                                <label htmlFor="lastName" className=" p-1 block text-sm text-gray-500">Last Name</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-4 font-semibold">Address</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="streetAdr" className="p-1 w-4/6"
                                value={this.props.streetAdr}/>
                                    <label htmlFor="streetAdr" className="p-1 block text-sm text-gray-500">Street Address</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="zip" className="p-1 w-2/6"
                                value={this.props.zip}/>
                                <label htmlFor="zip" className="p-1 block text-sm text-gray-500">ZIP Code</label>
                            </div>

                        </div>

                        <div className="mt-4 flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="city" className="p-1 w-4/6"
                                value={this.props.city}/>
                                <label htmlFor="city" className="p-1 block text-sm text-gray-500">City</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="country" className="p-1 w-4/6"
                                value={this.props.country}/>
                                <label htmlFor="country" className="p-1 block text-sm text-gray-500">Country</label>
                            </div>

                        </div>
                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <div className="flex-row">

                            <div className="w-3/6 inline-block">
                                <h2 className="mb-4 font-semibold">Phone Number</h2>
                            </div>

                            <div className="w-1/3 inline-block">
                                <h2 className="mb-4 font-semibold">Email</h2>
                            </div>

                        </div>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly type="text" id="phone" className="p-1 w-4/6"
                                value={this.props.phone}/>
                                <label htmlFor="phone" className="p-1 block text-sm text-gray-500">Number</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="email" className="p-1 w-4/6"
                                value={this.props.email}/>
                                <label htmlFor="email" className="p-1 block text-sm text-gray-500">Email</label>
                            </div>
                        </div>
                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-4 font-semibold">Category Selection</h2>

                        <div className="flex">

                            <div className="w-1/3">
                                <h2 className="font-semibold">Single </h2>
                                <input readOnly id="single" className="p-1 w-16"
                                value={this.props.singleRoomCount}/>
                                <label htmlFor="single" className="p-1 block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold">Double</h2>
                                <input readOnly id="double" className="p-1 w-16"
                                value={this.props.doubleRoomCount}/>
                                <label htmlFor="double" className="p-1 block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold">Superior</h2>
                                <input readOnly id="superior" className="p-1 w-16"
                                value={this.props.superiorRoomCount}/>
                                <label htmlFor="superior" className="p-1 block text-sm text-gray-500">Room Count</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-4 font-semibold">Length of stay</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly id="checkInDate" className="p-1"
                                value={this.props.checkInDate}/>
                                <label htmlFor="checkInDate" className="p-1 block text-sm text-gray-500">Check-In Date</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly id="checkOutDate" className="p-1"
                                value={this.props.checkOutDate}/>
                                <label htmlFor="checkOutDate" className="p-1 block text-sm text-gray-500">Check-Out Date</label>
                            </div>

                        </div>

                    </div>

                    <div className="flex w-full justify-between mt-8">

                        <button type="button" className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95"
                                onClick={this.backBookingSummary}>
                            Back
                        </button>

                        <button type="button" onClick={this.createBooking}
                                className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black opacity-95">
                            Create Booking
                        </button>
                    </div>

                </form>
            </div>
        );
    }
}
export default BookingSummaryComponent;
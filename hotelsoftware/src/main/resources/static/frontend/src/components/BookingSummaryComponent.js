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
            <div className="p-16 bg-gray-50 h-full w-full bg-no-repeat bg-cover" style={{backgroundImage: `url(${bed})`}}>

                <div className="py-2 h-1/12 mb-10 border-4 border-blue-200 rounded bg-gray-50 opacity-95">
                    <div className="w-full px-4 mb-6">
                        <div className="w-full bg-gray-400 relative">
                            <div className="ml-32 absolute top-1/2 left-0 h-1 bg-blue-400 w-2/3">
                                <div className="w-3 h-3 bg-blue-800 rounded-full absolute right-0 top-1/2 transform translate-x-1/2 -translate-y-1/2"></div>
                            </div>
                        </div>
                        <div className="ml-32 mt-3 pt-2 relative hidden sm:block">
                            <div className="absolute left-0"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Stay</span>
                            </div>
                            <div className="absolute left-1/3 pl-8"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Personal Data</span>
                            </div>
                            <div className="absolute left-2/3 pl-20"><span
                                className="inline-block transform -translate-x-1/2 text-m font-semibold text-blue-400 tracking-wider">Summary</span>
                            </div>
                        </div>
                    </div>
                </div>

                <form>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-2 font-semibold tracking-wider">Group/Company</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly type="text" id="gname" className="p-1 w-4/6 mb-0.5"
                                value={this.props.gname}/>
                                <label htmlFor="gname" className="p-1 block text-xs text-gray-500 tracking-tighter">NAME</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="voucher" className="p-1 w-2/6 mb-0.5"
                                value={this.props.voucher}/>
                                <label htmlFor="voucher" className="p-1 block text-xs text-gray-500 tracking-tighter">VOUCHER CODE</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-2 font-semibold tracking-wider">Full Name</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="firstName" className="p-1 w-4/6 mb-0.5"
                                value={this.props.firstName}/>
                                <label htmlFor="firstName" className="p-1 block text-xs text-gray-500 tracking-tighter">FIRST NAME</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="lastName" className="p-1 w-4/6 mb-0.5"
                                value={this.props.lastName}/>
                                <label htmlFor="lastName" className="p-1 block text-xs text-gray-500 tracking-tighter">LAST NAME</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-2 font-semibold tracking-wider">Address</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="streetAdr" className="p-1 w-4/6 mb-0.5"
                                value={this.props.streetAdr}/>
                                    <label htmlFor="streetAdr" className="p-1 block text-xs text-gray-500 tracking-tighter">STREET ADDRESS</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="zip" className="p-1 w-2/6 mb-0.5"
                                value={this.props.zip}/>
                                <label htmlFor="zip" className="p-1 block text-xs text-gray-500 tracking-tighter">ZIP</label>
                            </div>

                        </div>

                        <div className="mt-4 flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="city" className="p-1 w-4/6 mb-0.5"
                                value={this.props.city}/>
                                <label htmlFor="city" className="p-1 block text-xs text-gray-500 tracking-tighter">CITY</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="country" className="p-1 w-4/6 mb-0.5"
                                value={this.props.country}/>
                                <label htmlFor="country" className="p-1 block text-xs text-gray-500 tracking-tighter">COUNTRY</label>
                            </div>

                        </div>
                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <div className="flex-row">

                            <div className="w-3/6 inline-block tracking-wider">
                                <h2 className="mb-2 font-semibold">Phone Number</h2>
                            </div>

                            <div className="w-1/3 inline-block tracking-wider">
                                <h2 className="mb-2 font-semibold">Email</h2>
                            </div>

                        </div>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly type="text" id="phone" className="p-1 w-4/6 mb-0.5"
                                value={this.props.phone}/>
                                <label htmlFor="phone" className="p-1 block text-xs text-gray-500 tracking-tighter">NUMBER</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="email" className="p-1 w-4/6 mb-0.5"
                                value={this.props.email}/>
                                <label htmlFor="email" className="p-1 block text-xs text-gray-500 tracking-tighter">EMAIL</label>
                            </div>
                        </div>
                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-2 font-semibold tracking-wider">Category Selection</h2>

                        <div className="flex">

                            <div className="w-1/3">
                                <h2 className="font-semibold mb-1">Single </h2>
                                <input readOnly id="single" className="p-1 w-16 mb-0.5"
                                value={this.props.singleRoomCount}/>
                                <label htmlFor="single" className="p-1 block text-xs text-gray-500 tracking-tighter">ROOMS</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold mb-1">Double</h2>
                                <input readOnly id="double" className="p-1 w-16 mb-0.5"
                                value={this.props.doubleRoomCount}/>
                                <label htmlFor="double" className="p-1 block text-xs text-gray-500 tracking-tighter">ROOMS</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold mb-1">Superior</h2>
                                <input readOnly id="superior" className="p-1 w-16 mb-0.5"
                                value={this.props.superiorRoomCount}/>
                                <label htmlFor="superior" className="p-1 block text-xs text-gray-500 tracking-tighter">ROOMS</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-6 mb-4 border-4 border-blue-200 rounded bg-gray-100 opacity-95">

                        <h2 className="mb-2 font-semibold tracking-wider">Length of Stay</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly id="checkInDate" className="p-1 mb-0.5"
                                value={this.props.checkInDate}/>
                                <label htmlFor="checkInDate" className="p-1 block text-xs text-gray-500 tracking-tighter">CHECK-IN DATE</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly id="checkOutDate" className="p-1 mb-0.5"
                                value={this.props.checkOutDate}/>
                                <label htmlFor="checkOutDate" className="p-1 block text-xs text-gray-500 tracking-tighter">CHECK-OUT DATE</label>
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
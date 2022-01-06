import React from 'react';

class BookingSummaryComponent extends React.Component {

    constructor(props) {
        super(props);

        this.backBookingSummary = this.backBookingSummary.bind(this);
    }

    backBookingSummary()
    {
        this.props.onBackBookingSummary();
    }

    render() {
        return (
            <React.Fragment>
                <h1 className="text-3xl mb-4 font-semibold">Booking Summary</h1>

                <form>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Group/Company</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="gname" className="w-4/6"
                                value={this.props.gname}/>
                                <label htmlFor="gname" className="block text-sm text-gray-500">Name</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="voucher" className="w-2/6"
                                value={this.props.voucher}/>
                                <label htmlFor="voucher" className=" block text-sm text-gray-500">Voucher Code</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Full Name</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="firstName" className="w-4/6"
                                value={this.props.firstName}/>
                                <label htmlFor="firstName" className="block text-sm text-gray-500">First Name</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="lastName" className="w-4/6"
                                value={this.props.lastName}/>
                                <label htmlFor="lastName" className=" block text-sm text-gray-500">Last Name</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Address</h2>

                        <div className="flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="streetAdr" className="w-4/6"
                                value={this.props.streetAdr}/>
                                    <label htmlFor="streetAdr" className="block text-sm text-gray-500">Street Address</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="zip" className="w-2/6"
                                value={this.props.zip}/>
                                <label htmlFor="zip" className=" block text-sm text-gray-500">ZIP Code</label>
                            </div>

                        </div>

                        <div className="mt-4 flex row">

                            <div className="w-1/2 ">
                                <input readOnly type="text" id="city" className="w-4/6"
                                value={this.props.city}/>
                                <label htmlFor="city" className="block text-sm text-gray-500">City</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="country" className="w-4/6"
                                value={this.props.country}/>
                                <label htmlFor="country" className=" block text-sm text-gray-500">Country</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mb-2 border border-gray-300">

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
                                <input readOnly type="text" id="phone" className="w-4/6"
                                value={this.props.phone}/>
                                <label htmlFor="phone" className="block text-sm text-gray-500">Number</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly type="text" id="email" className="w-4/6"
                                value={this.props.email}/>
                                <label htmlFor="email" className=" block text-sm text-gray-500">Email</label>
                            </div>
                        </div>
                    </div>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Category Selection</h2>

                        <div className="flex">

                            <div className="w-1/3">
                                <h2 className="font-semibold">Single </h2>
                                <input readOnly id="single" className="w-16"
                                value={this.props.singleRoomCount}/>
                                <label htmlFor="single" className="block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold">Double</h2>
                                <input readOnly id="double" className="w-16"
                                value={this.props.doubleRoomCount}/>
                                <label htmlFor="double" className="block text-sm text-gray-500">Room Count</label>
                            </div>

                            <div className="w-1/3">
                                <h2 className="font-semibold">Superior</h2>
                                <input readOnly id="superior" className="w-16"
                                value={this.props.superiorRoomCount}/>
                                <label htmlFor="superior" className="block text-sm text-gray-500">Room Count</label>
                            </div>

                        </div>

                    </div>

                    <div className="p-4 mb-2 border border-gray-300">

                        <h2 className="mb-4 font-semibold">Length of stay</h2>

                        <div className="flex row">

                            <div className="w-1/2">
                                <input readOnly id="checkInDate"
                                value={this.props.checkInDate}/>
                                <label htmlFor="checkInDate" className="block text-sm text-gray-500">Check-In Date</label>
                            </div>

                            <div className="w-1/2">
                                <input readOnly id="checkOutDate"
                                value={this.props.checkOutDate}/>
                                <label htmlFor="checkOutDate" className="block text-sm text-gray-500">Check-Out Date</label>
                            </div>

                        </div>

                    </div>

                    <div className="flex w-full justify-between mt-6">

                        <div className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black"
                                onClick={this.backBookingSummary}>
                            Back
                        </div>

                        <button
                                className="w-1/5 p-1 rounded-lg border-2 border-opacity-75 border-blue-50 text-center bg-blue-300 hover:bg-blue-400 focus:bg-blue-500 text-xl text-black">
                            Create Booking
                        </button>
                    </div>

                </form>
            </React.Fragment>
        );
    }
}
export default BookingSummaryComponent;
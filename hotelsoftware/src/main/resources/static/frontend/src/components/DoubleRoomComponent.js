import React from 'react';
import doubleRoom from "../images/doubleRoom.jpg"


class DoubleRoomComponent extends React.Component {


    render() {
        return (
            <React.Fragment>
                <div className="grid grid-cols-3 gap-1 my-4">
                    <div>
                        <img src={doubleRoom} className="h-64"/>
                    </div>
                    <div >
                        <h2 className="font-bold mb-2">Double Room</h2>
                        <p>Stylish but with modern rooms - on 23 square meters.
                            The double rooms have high living comfort and in each room hangs a picture of a Vorarlberg artist.</p>

                        <h2 class="font-bold mt-6 mb-2">Room Details</h2>

                        <ul className="list-disc ml-8">
                            <li>balcony</li>
                            <li>bathtub / WC</li>
                            <li>flat screen tv</li>
                            <li>WLAN</li>
                        </ul>
                    </div>
                </div>
            </React.Fragment>
        );
    }
}
export default DoubleRoomComponent;
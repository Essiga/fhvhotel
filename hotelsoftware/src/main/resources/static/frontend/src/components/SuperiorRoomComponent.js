import React from 'react';
import superiorRoom from "../images/superiorRoom.jpg"

class SingleRoomComponent extends React.Component {

    render() {
        return (
        <React.Fragment>
            <div className="grid grid-cols-3 gap-1 my-4">
                <div>
                    <img src={superiorRoom} className="h-64"/>
                </div>
                <div className="h-64">
                    <h2 className="font-bold mb-2">Superior Room</h2>
                    <p>Room with space to dream - on 30 square meters.
                        All Superior Rooms allow a view of the entire Lech mountain panorama.</p>

                    <h2 class="font-bold mt-6 mb-2">Room Details</h2>

                    <ul className="list-disc ml-8">
                        <li>balcony</li>
                        <li>bathtub / WC</li>
                        <li>flat screen tv</li>
                        <li>WLAN</li>
                        <li>hairdryer and bath basket</li>
                    </ul>
                </div>
            </div>

        </React.Fragment>
        );
    }
}
export default SingleRoomComponent;




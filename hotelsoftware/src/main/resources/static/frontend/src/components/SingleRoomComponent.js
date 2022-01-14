import React from 'react';
import singleRoom from "../images/singleRoom.jpg"


class SingleRoomComponent extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="grid grid-cols-2 gap-4 mt-5">
                   <div>
                       <img src={singleRoom} className=""/>
                   </div>
                   <div>
                       <h2 className="font-bold mb-2">Single Room</h2>
                       <p>Stylish timeless furnishings with warm colors - on 18 square meters.
                           Simple room in pastel tones with a view of the local mountain</p>

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
export default SingleRoomComponent;
package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.LineItemDTO;

import java.util.List;

public class LineItemWrapper {

    private List<LineItemDTO> lineItemList;

    public LineItemWrapper(List<LineItemDTO> lineItems) {
        this.lineItemList = lineItems;
    }

    public List<LineItemDTO> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItemDTO> lineItems) {
        this.lineItemList = lineItems;
    }
}

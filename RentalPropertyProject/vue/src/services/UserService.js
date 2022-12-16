import axios from 'axios';
//axios is coming from package.json & we are going to use it 
//for making API calls

export default {

    getAllRenters() {
        return axios.get("/list-renters/:property-id");
    },
    addRenter(renter, propertyId){
        return axios.post(`/list-renters/${propertyId}`, renter);
    },
    getInvoicesByLandlordUserId() {
        return axios.get('/view-invoices-status')
    }
}
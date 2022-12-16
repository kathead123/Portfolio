import axios from 'axios';
//axios is coming from package.json & we are going to use it 
//for making API calls

export default {

    listInvoicesByUserId() {
        return axios.get("/list-invoices");
    },

    payInvoice(invoiceNumber) {
        return axios.put(`/pay-invoice/${invoiceNumber}`);
    }
}
import axios from 'axios';
//axios is coming from package.json & we are going to use it 
//for making API calls

export default {

    getProperties() {
        return axios.get("/");
    },

    addProperty(property) {
        return axios.post('/add-property', property);
    },
    getPropertiesByLandlordUserId() {
        return axios.get("/landlord-properties");
    },
    getPropertiesByPropertyId(propertyId) {
        return axios.get(`/get-property/${propertyId}`);
    }
}
import {request} from '../axios_helper';

const VENDOR_API_URL = "/api/v2/vendors";

class VendorService {

    getVendors(){
        return request("GET", VENDOR_API_URL, {});
    }

    createVendor(vendor){
        return request("POST", VENDOR_API_URL, vendor);
    }

    getVendorById(vendorId){
        return request("GET", VENDOR_API_URL + '/' + vendorId, {});
    }

    updateVendor(vendor, vendorId){
        return request("PUT", VENDOR_API_URL + '/' + vendorId, vendor);
    }

    deleteVendor(vendorId){
        return request("DELETE", VENDOR_API_URL + '/' + vendorId, {});
    }
}

export default new VendorService()
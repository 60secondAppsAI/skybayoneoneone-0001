import http from "../http-common"; 

class TravelDocumentService {
  getAllTravelDocuments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/travelDocument/travelDocuments`, searchDTO);
  }

  get(travelDocumentId) {
    return this.getRequest(`/travelDocument/${travelDocumentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/travelDocument?field=${matchData}`, null);
  }

  addTravelDocument(data) {
    return http.post("/travelDocument/addTravelDocument", data);
  }

  update(data) {
  	return http.post("/travelDocument/updateTravelDocument", data);
  }
  
  uploadImage(data,travelDocumentId) {
  	return http.postForm("/travelDocument/uploadImage/"+travelDocumentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new TravelDocumentService();

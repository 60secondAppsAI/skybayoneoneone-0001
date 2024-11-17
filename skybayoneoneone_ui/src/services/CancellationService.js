import http from "../http-common"; 

class CancellationService {
  getAllCancellations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/cancellation/cancellations`, searchDTO);
  }

  get(cancellationId) {
    return this.getRequest(`/cancellation/${cancellationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/cancellation?field=${matchData}`, null);
  }

  addCancellation(data) {
    return http.post("/cancellation/addCancellation", data);
  }

  update(data) {
  	return http.post("/cancellation/updateCancellation", data);
  }
  
  uploadImage(data,cancellationId) {
  	return http.postForm("/cancellation/uploadImage/"+cancellationId, data);
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

export default new CancellationService();

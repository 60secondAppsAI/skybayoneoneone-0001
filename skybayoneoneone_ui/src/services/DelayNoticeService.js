import http from "../http-common"; 

class DelayNoticeService {
  getAllDelayNotices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/delayNotice/delayNotices`, searchDTO);
  }

  get(delayNoticeId) {
    return this.getRequest(`/delayNotice/${delayNoticeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/delayNotice?field=${matchData}`, null);
  }

  addDelayNotice(data) {
    return http.post("/delayNotice/addDelayNotice", data);
  }

  update(data) {
  	return http.post("/delayNotice/updateDelayNotice", data);
  }
  
  uploadImage(data,delayNoticeId) {
  	return http.postForm("/delayNotice/uploadImage/"+delayNoticeId, data);
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

export default new DelayNoticeService();

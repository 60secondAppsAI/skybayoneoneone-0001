import http from "../http-common"; 

class InFlightMealService {
  getAllInFlightMeals(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/inFlightMeal/inFlightMeals`, searchDTO);
  }

  get(inFlightMealId) {
    return this.getRequest(`/inFlightMeal/${inFlightMealId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/inFlightMeal?field=${matchData}`, null);
  }

  addInFlightMeal(data) {
    return http.post("/inFlightMeal/addInFlightMeal", data);
  }

  update(data) {
  	return http.post("/inFlightMeal/updateInFlightMeal", data);
  }
  
  uploadImage(data,inFlightMealId) {
  	return http.postForm("/inFlightMeal/uploadImage/"+inFlightMealId, data);
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

export default new InFlightMealService();

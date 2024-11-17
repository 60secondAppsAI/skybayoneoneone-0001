<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <inFlightMeal-table
            v-if="inFlightMeals && inFlightMeals.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:inFlightMeals="inFlightMeals"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-in-flight-meals="getAllInFlightMeals"
             >

            </inFlightMeal-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import InFlightMealTable from "@/components/InFlightMealTable";
import InFlightMealService from "../services/InFlightMealService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    InFlightMealTable,
  },
  data() {
    return {
      inFlightMeals: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllInFlightMeals(sortBy='inFlightMealId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await InFlightMealService.getAllInFlightMeals(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.inFlightMeals.length) {
					this.inFlightMeals = response.data.inFlightMeals;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching inFlightMeals:", error);
        }
        
      } catch (error) {
        console.error("Error fetching inFlightMeal details:", error);
      }
    },
  },
  mounted() {
    this.getAllInFlightMeals();
  },
  created() {
    this.$root.$on('searchQueryForInFlightMealsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllInFlightMeals();
    })
  }
};
</script>
<style></style>

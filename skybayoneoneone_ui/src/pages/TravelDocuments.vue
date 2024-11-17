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
            <travelDocument-table
            v-if="travelDocuments && travelDocuments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:travelDocuments="travelDocuments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-travel-documents="getAllTravelDocuments"
             >

            </travelDocument-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TravelDocumentTable from "@/components/TravelDocumentTable";
import TravelDocumentService from "../services/TravelDocumentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TravelDocumentTable,
  },
  data() {
    return {
      travelDocuments: [],
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
    async getAllTravelDocuments(sortBy='travelDocumentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TravelDocumentService.getAllTravelDocuments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.travelDocuments.length) {
					this.travelDocuments = response.data.travelDocuments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching travelDocuments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching travelDocument details:", error);
      }
    },
  },
  mounted() {
    this.getAllTravelDocuments();
  },
  created() {
    this.$root.$on('searchQueryForTravelDocumentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTravelDocuments();
    })
  }
};
</script>
<style></style>

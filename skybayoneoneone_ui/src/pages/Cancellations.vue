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
            <cancellation-table
            v-if="cancellations && cancellations.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:cancellations="cancellations"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cancellations="getAllCancellations"
             >

            </cancellation-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CancellationTable from "@/components/CancellationTable";
import CancellationService from "../services/CancellationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CancellationTable,
  },
  data() {
    return {
      cancellations: [],
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
    async getAllCancellations(sortBy='cancellationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CancellationService.getAllCancellations(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.cancellations.length) {
					this.cancellations = response.data.cancellations;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching cancellations:", error);
        }
        
      } catch (error) {
        console.error("Error fetching cancellation details:", error);
      }
    },
  },
  mounted() {
    this.getAllCancellations();
  },
  created() {
    this.$root.$on('searchQueryForCancellationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCancellations();
    })
  }
};
</script>
<style></style>

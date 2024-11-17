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
            <delayNotice-table
            v-if="delayNotices && delayNotices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:delayNotices="delayNotices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-delay-notices="getAllDelayNotices"
             >

            </delayNotice-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DelayNoticeTable from "@/components/DelayNoticeTable";
import DelayNoticeService from "../services/DelayNoticeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DelayNoticeTable,
  },
  data() {
    return {
      delayNotices: [],
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
    async getAllDelayNotices(sortBy='delayNoticeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DelayNoticeService.getAllDelayNotices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.delayNotices.length) {
					this.delayNotices = response.data.delayNotices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching delayNotices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching delayNotice details:", error);
      }
    },
  },
  mounted() {
    this.getAllDelayNotices();
  },
  created() {
    this.$root.$on('searchQueryForDelayNoticesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDelayNotices();
    })
  }
};
</script>
<style></style>

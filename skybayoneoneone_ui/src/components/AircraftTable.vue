
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Aircrafts</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalAircrafts = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalAircrafts">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Aircraft</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="AircraftId" type="text" placeholder="Enter AircraftId" v-model="aircraftToAdd.aircraftId"></base-input>
  <base-input label="ModelName" type="text" placeholder="Enter ModelName" v-model="aircraftToAdd.modelName"></base-input>
  <base-input label="SeatingCapacity" type="text" placeholder="Enter SeatingCapacity" v-model="aircraftToAdd.seatingCapacity"></base-input>
  <base-input label="RegistrationNumber" type="text" placeholder="Enter RegistrationNumber" v-model="aircraftToAdd.registrationNumber"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="aircrafts" :row-key="record => record.AircraftId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <AircraftPictureView :aircrafts="aircrafts" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import AircraftService from "../services/AircraftService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import AircraftPictureView from './AircraftPictureView.vue';


const aircraftsColumns = [
  "aircraftId",
  "year",
  "date",
  "competitionId",
  "aircraftId"
]

export default {
  props: {
    aircrafts: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    AircraftPictureView
  },

  data() {
    return {
      modalAircrafts: false,
        isTableView: true,

      columns: [
        {
          title: 'Aircraft Id',
		dataIndex: 'aircraftId',
          visible: true,
          scopedSlots: { customRender: 'aircraftId' },
          sorter: true
          //sorter: (a, b) => a.aircraftId - b.aircraftId,
          //sorter: (a, b) => a.aircraftId.localeCompare(b.aircraftId),
        },
        {
          title: 'Model Name',
		dataIndex: 'modelName',
          visible: true,
          scopedSlots: { customRender: 'modelName' },
          sorter: true
          //sorter: (a, b) => a.modelName - b.modelName,
          //sorter: (a, b) => a.modelName.localeCompare(b.modelName),
        },
        {
          title: 'Seating Capacity',
		dataIndex: 'seatingCapacity',
          visible: true,
          scopedSlots: { customRender: 'seatingCapacity' },
          sorter: true
          //sorter: (a, b) => a.seatingCapacity - b.seatingCapacity,
          //sorter: (a, b) => a.seatingCapacity.localeCompare(b.seatingCapacity),
        },
        {
          title: 'Registration Number',
		dataIndex: 'registrationNumber',
          visible: true,
          scopedSlots: { customRender: 'registrationNumber' },
          sorter: true
          //sorter: (a, b) => a.registrationNumber - b.registrationNumber,
          //sorter: (a, b) => a.registrationNumber.localeCompare(b.registrationNumber),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} aircrafts`,
      },

      aircrafts: [],
      aircraftToAdd: {},

      aircraftsTable: {
        columns: [...aircraftsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'aircraftId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderAircraftsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let aircraftsTableData = [];
      for (let i = 0; i < this.aircrafts.length; i++) {
        aircraftsTableData.push({
          id: i,
          aircraftId: this.aircrafts[i].aircraftId,
          year: this.aircrafts[i].year,
          date: this.aircrafts[i].date,
          competitionId: this.aircrafts[i].competitionId,
          aircraftId: this.aircrafts[i].aircraftId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-aircrafts',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToAircraftDetail(id) {
      this.$router.push({ name: 'AircraftDetail', params: { aircraftId: id.toString() }})
    },
    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToCrewMemberDetail(id) {
      this.$router.push({ name: 'CrewMemberDetail', params: { crewMemberId: id.toString() }})
    },
    routingToPilotDetail(id) {
      this.$router.push({ name: 'PilotDetail', params: { pilotId: id.toString() }})
    },
    routingToFlightCrewDetail(id) {
      this.$router.push({ name: 'FlightCrewDetail', params: { flightCrewId: id.toString() }})
    },
    routingToCustomerSupportDetail(id) {
      this.$router.push({ name: 'CustomerSupportDetail', params: { customerSupportId: id.toString() }})
    },
    routingToLuggageDetail(id) {
      this.$router.push({ name: 'LuggageDetail', params: { luggageId: id.toString() }})
    },
    routingToCancellationDetail(id) {
      this.$router.push({ name: 'CancellationDetail', params: { cancellationId: id.toString() }})
    },
    routingToDelayNoticeDetail(id) {
      this.$router.push({ name: 'DelayNoticeDetail', params: { delayNoticeId: id.toString() }})
    },
    routingToCheckInDetail(id) {
      this.$router.push({ name: 'CheckInDetail', params: { checkInId: id.toString() }})
    },
    routingToBoardingPassDetail(id) {
      this.$router.push({ name: 'BoardingPassDetail', params: { boardingPassId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToFlightScheduleDetail(id) {
      this.$router.push({ name: 'FlightScheduleDetail', params: { flightScheduleId: id.toString() }})
    },
    routingToBaggageClaimDetail(id) {
      this.$router.push({ name: 'BaggageClaimDetail', params: { baggageClaimId: id.toString() }})
    },
    routingToTravelDocumentDetail(id) {
      this.$router.push({ name: 'TravelDocumentDetail', params: { travelDocumentId: id.toString() }})
    },
    routingToInFlightMealDetail(id) {
      this.$router.push({ name: 'InFlightMealDetail', params: { inFlightMealId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForAircraftsChanged', this.searchQuery);
		//this.renderAircraftsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalAircrafts = false;

      const currentDate = new Date().getTime();
      this.aircraftToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.aircraftToAdd);
      console.log(jsonData);
      
      const res = await AircraftService.addAircraft(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderAircraftsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>

import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import CrewMembers from  '@/pages/CrewMembers.vue';
import CrewMemberDetail from  '@/pages/CrewMemberDetail.vue';
import Pilots from  '@/pages/Pilots.vue';
import PilotDetail from  '@/pages/PilotDetail.vue';
import FlightCrews from  '@/pages/FlightCrews.vue';
import FlightCrewDetail from  '@/pages/FlightCrewDetail.vue';
import CustomerSupports from  '@/pages/CustomerSupports.vue';
import CustomerSupportDetail from  '@/pages/CustomerSupportDetail.vue';
import Luggages from  '@/pages/Luggages.vue';
import LuggageDetail from  '@/pages/LuggageDetail.vue';
import Cancellations from  '@/pages/Cancellations.vue';
import CancellationDetail from  '@/pages/CancellationDetail.vue';
import DelayNotices from  '@/pages/DelayNotices.vue';
import DelayNoticeDetail from  '@/pages/DelayNoticeDetail.vue';
import CheckIns from  '@/pages/CheckIns.vue';
import CheckInDetail from  '@/pages/CheckInDetail.vue';
import BoardingPasss from  '@/pages/BoardingPasss.vue';
import BoardingPassDetail from  '@/pages/BoardingPassDetail.vue';
import LoyaltyPrograms from  '@/pages/LoyaltyPrograms.vue';
import LoyaltyProgramDetail from  '@/pages/LoyaltyProgramDetail.vue';
import FlightSchedules from  '@/pages/FlightSchedules.vue';
import FlightScheduleDetail from  '@/pages/FlightScheduleDetail.vue';
import BaggageClaims from  '@/pages/BaggageClaims.vue';
import BaggageClaimDetail from  '@/pages/BaggageClaimDetail.vue';
import TravelDocuments from  '@/pages/TravelDocuments.vue';
import TravelDocumentDetail from  '@/pages/TravelDocumentDetail.vue';
import InFlightMeals from  '@/pages/InFlightMeals.vue';
import InFlightMealDetail from  '@/pages/InFlightMealDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/aircrafts',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewMembers',
		name: 'CrewMembers',
		layout: DefaultLayout,
		component: CrewMembers,
	},
	{
	    path: '/crewMember/:crewMemberId', 
	    name: 'CrewMemberDetail',
		layout: DefaultLayout,
	    component: CrewMemberDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/pilots',
		name: 'Pilots',
		layout: DefaultLayout,
		component: Pilots,
	},
	{
	    path: '/pilot/:pilotId', 
	    name: 'PilotDetail',
		layout: DefaultLayout,
	    component: PilotDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightCrews',
		name: 'FlightCrews',
		layout: DefaultLayout,
		component: FlightCrews,
	},
	{
	    path: '/flightCrew/:flightCrewId', 
	    name: 'FlightCrewDetail',
		layout: DefaultLayout,
	    component: FlightCrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customerSupports',
		name: 'CustomerSupports',
		layout: DefaultLayout,
		component: CustomerSupports,
	},
	{
	    path: '/customerSupport/:customerSupportId', 
	    name: 'CustomerSupportDetail',
		layout: DefaultLayout,
	    component: CustomerSupportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/luggages',
		name: 'Luggages',
		layout: DefaultLayout,
		component: Luggages,
	},
	{
	    path: '/luggage/:luggageId', 
	    name: 'LuggageDetail',
		layout: DefaultLayout,
	    component: LuggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellations',
		name: 'Cancellations',
		layout: DefaultLayout,
		component: Cancellations,
	},
	{
	    path: '/cancellation/:cancellationId', 
	    name: 'CancellationDetail',
		layout: DefaultLayout,
	    component: CancellationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/delayNotices',
		name: 'DelayNotices',
		layout: DefaultLayout,
		component: DelayNotices,
	},
	{
	    path: '/delayNotice/:delayNoticeId', 
	    name: 'DelayNoticeDetail',
		layout: DefaultLayout,
	    component: DelayNoticeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkIns',
		name: 'CheckIns',
		layout: DefaultLayout,
		component: CheckIns,
	},
	{
	    path: '/checkIn/:checkInId', 
	    name: 'CheckInDetail',
		layout: DefaultLayout,
	    component: CheckInDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/boardingPasss',
		name: 'BoardingPasss',
		layout: DefaultLayout,
		component: BoardingPasss,
	},
	{
	    path: '/boardingPass/:boardingPassId', 
	    name: 'BoardingPassDetail',
		layout: DefaultLayout,
	    component: BoardingPassDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltyPrograms',
		name: 'LoyaltyPrograms',
		layout: DefaultLayout,
		component: LoyaltyPrograms,
	},
	{
	    path: '/loyaltyProgram/:loyaltyProgramId', 
	    name: 'LoyaltyProgramDetail',
		layout: DefaultLayout,
	    component: LoyaltyProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightSchedules',
		name: 'FlightSchedules',
		layout: DefaultLayout,
		component: FlightSchedules,
	},
	{
	    path: '/flightSchedule/:flightScheduleId', 
	    name: 'FlightScheduleDetail',
		layout: DefaultLayout,
	    component: FlightScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggageClaims',
		name: 'BaggageClaims',
		layout: DefaultLayout,
		component: BaggageClaims,
	},
	{
	    path: '/baggageClaim/:baggageClaimId', 
	    name: 'BaggageClaimDetail',
		layout: DefaultLayout,
	    component: BaggageClaimDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/travelDocuments',
		name: 'TravelDocuments',
		layout: DefaultLayout,
		component: TravelDocuments,
	},
	{
	    path: '/travelDocument/:travelDocumentId', 
	    name: 'TravelDocumentDetail',
		layout: DefaultLayout,
	    component: TravelDocumentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/inFlightMeals',
		name: 'InFlightMeals',
		layout: DefaultLayout,
		component: InFlightMeals,
	},
	{
	    path: '/inFlightMeal/:inFlightMealId', 
	    name: 'InFlightMealDetail',
		layout: DefaultLayout,
	    component: InFlightMealDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;

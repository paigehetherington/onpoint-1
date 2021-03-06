# onpoint
The Iron Yard
17 A Princess Street
Charleston, SC 29401
•	OnPoint
From the minds of Paige & Candace
4th April 2016
Pitch - OnPoint is a website which highlights service organizations which provide acupuncture to underserved populations in remote areas of the world. It is a resource to link volunteer practitioners to service opportunities and link general users to make donations.
Technology:
1.     Javascript
2.     Angular
3.     HTML/CSS
4.     NodeJS
5.     JQuery
6.     Google Maps- API
7.     Java
-Spring
8.     H2 database
MVP Features:
1. Admin has ability to perform CRUD operations
       	-create organizations
       	- edit organizations
       	- delete organizations
       	- adding new photos and news stories
2. Any user can click a link to the organization to sign up to volunteer or donate.
?//3. Any user can click on a pin on the map to see which organizations have projects in a specific region (or simply search by region).
4. Volunteer practitioners can login and create a profile where they share photos and descriptions of their trips. Users can comment on trip experience.

USER STORIES:

Home Page
Information about Acupuncture and the call to action.  This page will link to the list of service organization profiles, volunteer practitioner blog, and login for volunteers.

Admin User - CRUD
Size: Medium

Value Statement: As an admin I need the ability to perform CRUD operations to the list of organizations so that they are effectively displayed for users.

Assumptions: None
Acceptance Criteria:
·      Ability to create new service organizations
·      Ability to update service organizations
·      Ability to delete service organizations
·      Ability to update photos and news stories
·      Add new locations for volunteer opportunities ?if not using google maps?

Service Organization Profiles
Size: Medium
Value Statement:
Profiles will contain organization name, regions of service, general project information and photo gallery. It will include a volunteer link and a donate link. Profiles will be displayed as a list on a page.



Potential Volunteer
Size: Medium
Value Statement: As  a volunteer practitioner I need access to a list of volunteer opportunities so that I can travel and be of service.

Assumptions: None
Acceptance Criteria:
·      Ability to see the list of service organizations
·      Ability to search by location?
·      Ability to donate
·      Ability to click on links to service organization websites to sign up to volunteer.
·      Ability to view volunteer profiles and make comments

Donor

Size: Medium

Value Statement: As a donor I need a list of service organizations so that I can choose a specific project or region to help provide acupuncture to those who don’t have access to medical care.

Assumptions: None
Acceptance Criteria:
·      Ability to see the list of service organizations
·      Ability to search by location?
·      Ability to donate
·      Ability to see volunteer profiles, photos and news stories and make comments

Volunteer Login/Create Profile

Size: Medium

Value Statement: As a practitioner I need the ability to login in order to create a profile so I can share my experience as a volunteer to inspire others to donate and/or volunteer. (I also need to be able to stay connected with other volunteers via a message board).

Assumptions:  None

Acceptance Criteria:
Ability to login 
Ability to create a profile
Ability to add photos and text to profile

Volunteer Profile - Update

Size: Small

Value Statement: As an OnPoint volunteer practitioner, I need the ability to edit and update my profile. This may include name, contact, and trip photos and info.
Assumptions: 
Volunteer has a profile and is logged in
Volunteer has completed a service trip with an organization listed on the site

Acceptance Criteria: 
Ability to update profile fields

Volunteer Profile - Delete

Size: Small

Value Statement: As a volunteer I need to be able to completely remove my profile from the site in case I no longer wish to share my experience with others.

Assumptions: Volunteer has a profile account

Acceptance Criteria: 
Volunteer can successfully remove profile

About Us

Size: Medium

Value Statement: As an Admin I need people to know the mission of the site so they can feel more inspired to get involved by donating time or money.
Acceptance Criteria: Highlights site’s mission statement and show a video about acupuncture volunteerism.

Photo Gallery

Size: M

Value Statement: As an organization I need to show more photos representing our efforts so people will be moved to donate or volunteer.

Acceptance Criteria: Page shows a sliding photo gallery for each organization

Contact

Size: Small

Value Statement: As an Admin I need organizations to contact me so I can list them on the site and continue to grow awareness and promote this type of service work.

Acceptance Criteria: Lists contact name and email address and links to social media accounts.



// Stretch goal! Message Board
Message - Create

Size: Small

Value Statement: As a volunteer I need to be able to post messages so we can share advice/answer questions for future volunteers and relate about our experiences in general with other volunteers.

Assumptions: Volunteer has a profile account

Acceptance Criteria: 
Volunteer can successfully post a message to message board

Message - Update

Size: Small

Value Statement: As a volunteer I need to be able to edit my messages in case I need to change what I wrote.

Assumptions: 
Volunteer is logged into their profile
Volunteer created the message that is being updated

Acceptance Criteria:
Volunteer can successfully update and save message to server

Message - Delete

Size: Small

Value Statement: As a volunteer I need to be able to entirely delete any messages I posted to the message board

Assumptions: Volunteer is logged into profile account
Volunteer created message that is being deleted

Acceptance Criteria: 
Volunteer can successfully delete a message post entirely



Message Board 

Size: Medium

Value Statement: Message Board will contain a list of messages posted by volunteers with profile accounts. They will be listed most recent first. A volunteer who is logged in will have the ability to edit/delete messages that he/she created.





























WireFrames

Home Page

Volunteer Create Account



Login and About page




COLLECTIONS:

Service Organizations

Volunteers

Volunteer Profiles

Comments
ROUTES:

/home
/service_org
/volunteer
/volunteer_profile
/comment
/about
/photo_gallery
/press
/contact
/logout

SPRINT MAP

4/6 - 4/8 	C: General setup, Home page, Volunteer Login (About, Contact)
		P: login route, content for service orgs, about page

4/8-4/11	C: Service orgs page, volunteer link, donation link (rating system)
		P: Service org routes

4/11-4/13	C: Volunteer profiles page, Comments
		P: Volunteer profiles routes, content for press

4/13-4/15	C: Photo Gallery, Press

4/15-4/18	Stretch: 	Admin login
				Google maps/search by location
				Rating system

4/18-4/20



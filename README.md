# HMOManagment
<h3>homework assignment- Hadasim</h3>
<h5>Efrat Eisenbach 214713513</h5>
In this project I implemented a server side System for management Health maintenance organization.<br>
For displaying the data, I used Postman, an application that allows testing APIs.<br>

# Actions:
1.add new hmo member+profile picture<br>
  (adding picture using dto)<br>
2.add corona details for exist hmo member<br>
3.get all existing hmo members<br>
4.get existing hmo member by id/phone<br>
5.get all corona details<br>
6.get the count of Hmo members which are unvaccinated<br>
7.get how many active patients were there each day in the last month.<br>
(Uploading corona details separately, assuming that not every hmo member has corona details, and this is something beyond the user's personal details)<br>

# Technologies & dependencies:
- SpringBootApplication
- spring-boot-starter-validation
- h2 database

# examples
example for upload hmo member:
<img width="632" alt="צילום מסך 2024-03-28 171217" src="https://github.com/efratizen/HMOManagment/assets/148067827/eb5d4219-8643-45bf-85c4-ed876d1b1580">

example for upload corona details:
<img width="633" alt="צילום מסך 2024-03-28 171520" src="https://github.com/efratizen/HMOManagment/assets/148067827/a135b588-eb66-4e67-8eb2-c0af742b3625">

example for invalid input(future date for date of birth):
<img width="629" alt="צילום מסך 2024-03-28 172241" src="https://github.com/efratizen/HMOManagment/assets/148067827/18520ece-fe4f-4fa4-bee6-8a7707a01d8f">







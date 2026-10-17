console.log("Welcome to PlaceTrack!");
// Search companies

function searchCompanies() {

    let input = document.getElementById("searchInput")
        .value.toLowerCase();

    let cards = document.querySelectorAll(".company-card");

    cards.forEach(function(card) {

        let companyName = card.querySelector("h2")
            .innerText.toLowerCase();

        let role = card.querySelector(".role")
            .innerText.toLowerCase();

        if (companyName.includes(input) || role.includes(input)) {
            card.style.display = "block";
        } else {
            card.style.display = "none";
        }

    });
}


// Demo application button

function applyForJob(companyName) {

    let applications = JSON.parse(
        localStorage.getItem("applications")
    ) || [];

    // Prevent duplicate applications
    if (applications.includes(companyName)) {
        alert("You have already selected this company!");
        return;
    }

    applications.push(companyName);

    localStorage.setItem(
        "applications",
        JSON.stringify(applications)
    );

    alert("Successfully added " + companyName +
          " to your applications!");

}
// Display applications

function displayApplications() {

    let applications = JSON.parse(
        localStorage.getItem("applications")
    ) || [];

    let applicationList = document.getElementById("applicationList");

    // Check if application page exists
    if (!applicationList) {
        return;
    }

    // If no applications
    if (applications.length === 0) {

        applicationList.innerHTML = `
            <div class="empty-message">

                <h2>No Applications Yet!</h2>

                <p>You haven't selected any companies.</p>

                <a href="companies.html" class="browse-btn">
                    Explore Companies
                </a>

            </div>
        `;

        return;
    }

    // Display application cards

    applicationList.innerHTML = applications.map(function(company) {

        return `
            <div class="application-card">

                <div>
                    <h3>${company}</h3>

                    <p>Placement Application</p>

                    <span class="application-status">
                        Selected
                    </span>
                </div>

                <button
                    class="remove-btn"
                    onclick="removeApplication('${company}')">

                    Remove

                </button>

            </div>
        `;

    }).join("");

}


// Remove application

function removeApplication(companyName) {

    let applications = JSON.parse(
        localStorage.getItem("applications")
    ) || [];

    applications = applications.filter(function(company) {
        return company !== companyName;
    });

    localStorage.setItem(
        "applications",
        JSON.stringify(applications)
    );

    displayApplications();

}


// Run when page loads

document.addEventListener("DOMContentLoaded", function() {

    displayApplications();

});
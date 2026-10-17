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
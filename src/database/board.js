const ROW_PER_PAGE = 10;

const boards = [
    { brdNo: 1, title: "ABC", content: "Nowadays, every company aims to create a high-quality web solution within a short time frame." },
    { brdNo: 2, title: "DEF",  content: "To put it into practice, the developers’ community chooses from many JavaScript libraries and frameworks and debates to find the best one." },
    { brdNo: 3, title: "GHK",  content: "At Codica, we consider Vue.js and ReactJS among the most rapidly developing tools for the front-end development for many reasons." },
    { brdNo: 4, title: "IJL",  content: "While these technologies can help create the same products and applications, both of them have pros and cons." },
    { brdNo: 5, title: "ABC1",  content: "The purpose of this article is to compare Vue.js vs React.js in different aspects: from general information to technical features." },
    { brdNo: 6, title: "DEF1",  content: "Both these web development tools have mature communities" },
    { brdNo: 7, title: "GHK1",  content: "wide support and popularity, but Vue.js is a framework and React is a library." },
    { brdNo: 8, title: "IJL1",  content: "So, why do we compare oranges and apples?" },
    { brdNo: 9, title: "ABC2",  content: "One of the key factors for the comparison between React and Vue was that Evan You" },
    { brdNo: 10, title: "DEF2",  content: "Vue.js framework creator, used ReactJS as a source of inspiration for new framework development." },
    { brdNo: 11, title: "GHK2",  content: "We will start our comparison of the two most popular technologies in question with a brief overview" },
    { brdNo: 12, title: "IJL2",  content: "Check the table below to learn the basic info about Vue vs React." },
]

export function getBoards(curPage, rowPerPage) {
    let rpp = rowPerPage || ROW_PER_PAGE;
    let epi = (curPage || 1);
    let spi = epi - 1;

    return boards.slice(spi*rpp, epi*rpp)
}

export function getTotCount() {
    return boards.length
}

export function getBoard(brdNo) {
    console.log(brdNo)
    const board = boards.find((obj) => obj.brdNo == brdNo)
    console.log(board)
    return board
}
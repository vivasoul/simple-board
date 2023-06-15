import {ref} from "vue"
import {getBoard} from "@/data/api/board"
/* search-input */
const sCatNo = ref(-1)
const sCurPage = ref(1)
const sMode = ref(0)
const sText = ref("")
/* search-output */
const boards = ref([])
const maxPage = ref(0)

async function loadBoards() {
    const postData = {
        catNo: sCatNo.value,
        curPage: sCurPage.value,
        mode: sMode.value,
        text: sText.value
    }
    const data = await getBoard(postData)

    boards.value = data.boards
    maxPage.value = data.page.maxPage
}

function searchAll() {
    sCatNo.value = -1
    sCurPage.value = 1
    sText.value = ""
    loadBoards().then()
}
function searchCategory(_catNo) {

    //if(catNo.value != _catNo) {
        sCatNo.value = _catNo
        sCurPage.value = 1
        sText.value = ""
        loadBoards().then()
    //} else {
    //   console.log("skip loading: same cat-no")
    //}
}

function searchPage(_curPage) {
    if(_curPage) {
        sCurPage.value = _curPage
    }
    loadBoards().then()
}

function searchText(_mode, _text) {
    sMode.value = _mode
    sText.value = _text
    sCurPage.value = 1
    loadBoards().then()
}

export default function useBoardSearch() {

    return {
        sCatNo,
        sCurPage,
        sMode,
        sText,
        boards,
        maxPage,
        searchAll,
        searchCategory,
        searchPage,
        searchText
    }
}
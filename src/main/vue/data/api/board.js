import axios from "axios"

export async function getBoard({catNo, curPage, mode, text}) {
    const _mode = text === undefined ? "" : (mode || "")
    const _text = encodeURIComponent(text || "")

    const res = await axios.get(`/board?catNo=${catNo || -1}&curPage=${curPage}&mode=${_mode}&text=${_text}`)

    if(res.status == 200) {
        return  res.data
    } else {
        return null
    }
}

export async function getBoardDetail(brdNo) {
    const res = await axios.get(`/board/${brdNo}`)

    if(res.status == 200) {
        return  res.data
    } else {
        return null
    }
}

export async function createBoard({title, content, catNos, regId, passwd, files}) {
    const res = await axios.post("/board", {title, content, catNos, regId, passwd, files})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function updateBoard({brdNo, title, content, catNos, files}) {
    const res = await axios.put(`/board/${brdNo}`, {brdNo, title, content, catNos, files})

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function deleteBoard(brdNo) {
    const res = await axios.delete(`/board/${brdNo}`)

    if(res.status == 200) {
        return true;
    } else {
        return false;
    }
}

export async function checkBoard({brdNo, passwd}) {
    const res = await axios.post(`/board/${brdNo}/check-pass`, {brdNo, passwd})

    if(res.status == 200) {
        return res.data;
    } else {
        return false;
    }
}
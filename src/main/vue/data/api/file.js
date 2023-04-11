import axios from "axios";
export async function uploadFiles(postData) {
    try {
        const res = await axios.post("/files/upload", postData)

        if (res.status == 200) {
            return res.data
        } else {
            return res.statusText
        }
    }catch(e) {
        console.log(e)
    }
}
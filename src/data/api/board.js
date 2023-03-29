import axios from "axios"

export async function getBoard() {
    const response = await axios.get("/board")

    console.log(response);

}
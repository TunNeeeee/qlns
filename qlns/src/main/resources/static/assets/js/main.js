/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 4:49 PM - 29/09/2024
 * User: lam-nguyen
 **/

const SORT_TYPE = {
    ASC: "asc",
    DESC: "desc",
    None: "none"
}


$(document).ready(function () {
    $("img").each((index, img) => {
        const size = $(img).data("size");
        $(img).css({width: `${size}px`, height: `${size}px`});
    })

    $("*").each((index, element) => {
        const width = $(element).data("width");
        const height = $(element).data("height");
        if (width) $(element).css({width: `${width}px`});
        if (height) $(element).css({height: `${height}px`});
    })
})

const loadFrameTable = (id) => {
    const table = $(`table${id}.ln-table`);
    const head = table.find("thead");
    if (head.hasClass("pin-top")) head.addClass("position-sticky top-0 start-0");
    if (table.find("tfoot").hasClass("pin-bottom")) table.find("tfoot").addClass("position-sticky bottom-0 start-0");
    table.find("tr").css({display: "flex", "align-items": "center"})
    head.find("td.sortable, th.sortable").each((index, col) => {
        const oldHtml = $(col).html();
        const newHtml = `
     <button class="bg-none border-0 d-flex gap-2 align-items-center btn-sort">
                <strong>${oldHtml}</strong>
                <p class="d-flex flex-column justify-content-center align-items-center">
                  <img style="width: 8px; height: 7px" src="assets/icon/bxs--up-arrow.png" alt="up">
                  <img style="width: 8px; height: 7px" src="assets/icon/bxs--down-arrow.png"
                     alt="down">
                </p>
     </button>
    `
        $(col).html(newHtml)
    })
    table.find("td, th").each((index, col) => {
        const size = $(col).data("size");
        if (!size) return;

        $(col).css({width: size})
    })
    repaintTable(id)
    $(".btn-sort").click(function () {
        const status = $(this).data("status");
        $(this).find("img").css({opacity: 0})
        switch (status) {
            case undefined:
            case SORT_TYPE.ASC:
                $(this).data("status", SORT_TYPE.DESC)
                $(this).find("img").eq(0).css({opacity: 1})
                break;
            case SORT_TYPE.DESC:
                $(this).data("status", SORT_TYPE.None)
                $(this).find("img").eq(1).css({opacity: 1})
                break;
            default:
                $(this).data("status", SORT_TYPE.ASC)
                $(this).find("img").css({opacity: 1}).show()
        }
    })
}

const repaintTable = (id) => {
    const sizeCell = [];
    const table = $(`table${id}.ln-table`);
    const maxRow = table.data("max-row");
    table.find("thead th, thead td").each((index, col) => {
        sizeCell.push($(col).data("width") ?? $(col).outerWidth())
    })

    const body = table.find("tbody");
    const row = body.find("tr");
    const styleRow = body.data("style") ?? "";
    body.removeAttr("data-style")
    row.addClass(styleRow)
    const totalCellInRow = row.eq(0).find(">*").length ?? 0;
    $(`table${id}.ln-table tbody th, table${id}.ln-table tbody td`).each((index, col) => {
        $(col).css({width: `${sizeCell[index % totalCellInRow]}px`, overflow: "hidden"})
    })

    if (!maxRow) return;
    const borderStyle = table.attr("class").toString().split(" ").filter((value) => value.includes("border"));
    borderStyle.forEach((value) => table.removeClass(value))
    table.prev()
        .after(`<div class="overflow-y-auto ${borderStyle.join(" ")}" style="max-height: ${row.eq(0).outerHeight() * maxRow + table.find("thead").outerHeight()}px">
                    ${table[0].outerHTML}
               </div>`)


    table.remove()
}

function uuidv4() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'
        .replace(/[xy]/g, function (c) {
            const r = Math.random() * 16 | 0,
                v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
}

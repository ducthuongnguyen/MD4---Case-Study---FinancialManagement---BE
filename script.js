function showCreateForm() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/wallets",
        success: function (wallet) {
            let content = "";
            for (let i = 0; i < wallet.length; i++) {
                content += `<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Create New Product</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
<!--                            <form id="createProduct">-->
                                      <div class="input-group mb-3">
                                        <span class="input-group-text">Wallet</span>` +
                    `<select class=\"form-select\" aria-label=\"Default select example\" id=\"wallet\">` +
                    getCategory(wallet) +
                    `</select>` +
                    `</div>` +

                    `<div class="input-group mb-3">
                            <button style="width: 232px" type="button" class="btn btn-danger btn-md" onclick="getExpense()">Expense</button>` +
                    `<button style="width: 232px" type="button" class="btn btn-primary btn-md" onclick="getIncome()">Income</button>
                                </div>` +

                    `<div id="showCategory"></div>` +

                    `<div class="input-group mb-3">
                                    <span class="input-group-text">Amount</span>
                                    <input type="number" class="form-control" placeholder="Enter the amount..." aria-label="name" aria-describedby="basic-addon1" id="moneyAmount">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Note</span>
                                    <textarea type="text" class="form-control" placeholder="Enter the note..." aria-label="price" aria-describedby="basic-addon1" id="note"></textarea>
                                </div>
                            
<!--                            </form>-->
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="createNewTransaction()">Save</button>
                        </div>
                    </div>
                </div>
            </div>`
            }
            document.getElementById("display1").innerHTML = content;
        }
    })

}

showCreateForm();

function getCategory(data) {
    let str = "";
    for (let i = 0; i < data.length; i++) {
        str += `<option value="${data[i].id}">${data[i].name}</option>`
    }
    return str;
}

function createNewTransaction() {


    let wallet = document.getElementById("wallet").value;
    let childCategory = document.getElementById("childCategory").value;
    let moneyAmount = document.getElementById("moneyAmount").value;
    let note = document.getElementById("note").value;
    let pro = {

        wallet: {
            id: wallet
        },
        childCategory: {
            id: childCategory
        },
        moneyAmount: moneyAmount,
        note: note
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        type: 'POST',
        url: 'http://localhost:8081/transactions/create',
        data: JSON.stringify(pro),
        success: function (data) {
            let id = data.wallet.id;
            let idChild = data.childCategory.id;
            $.ajax({
                type: "GET",
                url: "http://localhost:8081/wallets/" + id,
                success: function (wallet) {
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8081/childCategory/" + idChild,
                        success: function (child) {
                            let idParent = child.parentCategory.id;
                            let amountWallet = wallet.moneyAmount;
                            let amountWalletNew;
                            let amountNew = data.moneyAmount;
                            if (idParent===1){
                                amountWalletNew = amountWallet - amountNew;
                            }
                            if (idParent===2){
                                amountWalletNew = amountWallet + amountNew;
                            }
                            let pro = {
                                id: id,
                                name: wallet.name,
                                icon: wallet.icon,
                                moneyAmount: amountWalletNew,
                                moneyType: {
                                    id: wallet.moneyType.id
                                },
                                appUser: {
                                    id: wallet.appUser.id
                                }
                            }

                            $.ajax({
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json',
                                },
                                type: 'PUT',
                                url: "http://localhost:8081/wallets/" + id,
                                data: JSON.stringify(pro),
                                success: function () {

                                    findAllTransaction();
                                    findAllWallet();
                                },
                                error: function (error) {
                                    console.log(error)
                                }
                            })
                        }
                    })
                }
            })
        }
    })
}

function getExpense() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/childCategory/getChildCategory/1",
        success: function (child1) {
            let str = `</div>
                                      <div class="input-group mb-3">
                                        <span class="input-group-text">Tăng</span>` +
                `<select class=\"form-select\" aria-label=\"Default select example\" id= \"childCategory\">` +
                getCategory(child1) +
                `</select>` +
                `</div>`;
            document.getElementById("showCategory").innerHTML = str
        }

    })
}

function getIncome() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/childCategory/getChildCategory/2",
        success: function (child2) {
            let str =
                `<div class="input-group mb-3">
                                        <span class="input-group-text">Giảm</span>` +
                `<select class=\"form-select\" aria-label=\"Default select example\" id=\"childCategory\">` +
                getCategory(child2) +
                `</select>` +
                `</div>`;
            document.getElementById("showCategory").innerHTML = str;
        }
    })
}

function findAllTransaction() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/transactions",
        success: function (data) {
            console.log(data)
            display(data)
        }, error: function (error) {
            console.log(error);
        }
    })
}

function display(data) {
    let content = "";
    console.log(data)
    for (let i = 0; i < data.length; i++) {
        content += getTransaction(data[i]);
    }
    document.getElementById("showTransaction").innerHTML = content;
}

function getTransaction(transaction) {

    return `<tr>
            <td>${transaction.wallet.name}</td>
            <td>${transaction.childCategory.name}</td>
            <td>${transaction.moneyAmount}</td>
            <td>${transaction.createdDate}</td>
            <td>${transaction.note}</td>
            <td>
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal2" data-bs-whatever="@mdo" onclick="showEditForm(${transaction.id})"><i class="bi bi-pen-fill"></i></button>
                </td>
            <td><button type="button" class="btn btn-success" onclick="deleteTransaction(${transaction.id})"><i class="bi bi-trash3-fill"></i></button></td>
        </tr>`

}

findAllTransaction();
let idEdit = 0;
function showEditForm(id) {
    idEdit = id;
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/transactions/" + id,
        success: function (transaction) {
            $("#wallet2").val(transaction.wallet.id);
            $("#childCategory3").val(transaction.childCategory.id);
            $("#moneyAmount2").val(transaction.moneyAmount);
            $("#note2").val(transaction.note);
        }
    })
}

function getWallet() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/wallets",
        success: function (data) {
            let str = "";
            for (let i = 0; i < data.length; i++) {
                str += `<option value="${data[i].id}">${data[i].name}</option>`
            }
            $("#wallet2").html(str);
        }
    })
}

getWallet();

function deleteTransaction(id) {
    $.ajax({
        type: "DELETE",
        //tên API
        url: "http://localhost:8081/transactions/" + id,
        //xử lý khi thành công
        success: function () {
            findAllTransaction();
        }

    });
}

function getExpense1() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/childCategory/getChildCategory/1",
        success: function (child1) {

            let str = `</div>
                                      <div class="input-group mb-3">
                                        <span class="input-group-text">Tăng</span>` +
                `<select class=\"form-select\" aria-label=\"Default select example\" id= \"childCategory1\">` +
                getCategory(child1) +
                `</select>` +
                `</div>`;
            document.getElementById("showCategory1").innerHTML = str
        }

    })
}

function getIncome1() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/childCategory/getChildCategory/2",
        success: function (child2) {
            let str =
                `<div class="input-group mb-3">
                                        <span class="input-group-text">Giảm</span>` +
                `<select class=\"form-select\" aria-label=\"Default select example\" id=\"childCategory2\">` +
                getCategory(child2) +
                `</select>` +
                `</div>`;
            document.getElementById("showCategory1").innerHTML = str;
        }
    })
}


function update(id) {
    id = idEdit;
    $('#exampleModal2').modal('hide');
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/transactions/" + id,
        success: function (transactions) {
            console.log(transactions.childCategory.id)
            let wallet = document.getElementById("wallet2").value;
            let childCategory = document.getElementById("childCategory3").value;
            if (childCategory===null){
                childCategory=transactions.childCategory.id;
            }
            let moneyAmount = document.getElementById("moneyAmount2").value;
            let note = document.getElementById("note2").value;
            let pro = {
                id: id,
                wallet: {
                    id: wallet
                },
                childCategory: {
                    id: childCategory
                },
                moneyAmount: moneyAmount,
                note: note
            }
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                type: 'PUT',
                url: 'http://localhost:8081/transactions/' + id,
                data: JSON.stringify(pro),
                success: function () {
                    findAllTransaction();
                },
                error: function (error) {
                    console.log(error)
                }
            })
        }
    })
}

function findAllWallet() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/wallets",
        success: function (data) {
            let content = `<tr>
                        <th>Wallet</th>
                        <th>Amount</th>
                    </tr>`;
            console.log(data)
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
            <td><a href="#" onclick="findAllProductByCategory(${data[i].id})">${data[i].name}</a></td>
            <td>${data[i].moneyAmount}</a></td>
        </tr>`;
            }
            document.getElementById("display").innerHTML = content;
        }, error: function (error) {
            console.log(error);
        }
    })
}
findAllWallet();
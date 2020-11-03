package com.example

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import kotlin.collections.ArrayList

class TheCloudController {
    fun getAll(): ArrayList<SignatureContracts> {
        val signatureContract: ArrayList<SignatureContracts> = arrayListOf() // .toString("dd-MM-yyyy") для дат
        transaction {
            Signature_Contract.selectAll().map {
                signatureContract.add(
                    SignatureContracts(
                        idSign = it[Signature_Contract.idSign],
                        typeSign = it[Signature_Contract.typeSign],
                        dateSign = it[Signature_Contract.dateSign].toString("dd-MM-yyyy"),
                        idContract = it[Signature_Contract.idContract]
                    )
                )
            }
        }
        return signatureContract
    }

    fun getAddressName(): Pair<ArrayList<Addresses>, ArrayList<Cities>> {
        val address: ArrayList<Addresses> = arrayListOf()
        val city: ArrayList<Cities> = arrayListOf()
        transaction {
            Join(
                Address, City, onColumn = Address.idCity, otherColumn = City.idCity,
                joinType = JoinType.INNER,
            ).slice(Address.idAdr, Address.name, City.name)
                .select { Address.idAdr.greaterEq(2) and City.name.like("A%") }.forEach() {
                    address.add(
                        Addresses(
                            idAdr = it[Address.idAdr],
                            name = it[Address.name]
                        )
                    )
                    city.add(
                        Cities(
                            name = it[City.name]
                        )
                    )
                }
        }
        return Pair(address, city)
    }

    fun getPaymentCustomer(): Triple<ArrayList<Addresses>,ArrayList<Customers> ,ArrayList<Payments>> {
        val address: ArrayList<Addresses> = arrayListOf()
        val customer : ArrayList<Customers> = arrayListOf()
        val payment : ArrayList<Payments> = arrayListOf()

        transaction {
            (Address.leftJoin(Customer).leftJoin(Participants).leftJoin(Contract).leftJoin(Payment)
            ).slice(Address.name, Customer.FIO, Payment.size)
                .select { Address.name.eq("Lenina") and Payment.size.less(500) }.forEach() {
                    address.add(
                        Addresses(
                            name = it[Address.name]
                        )
                    )
                    customer.add(
                        Customers(
                            FIO = it[Customer.FIO]
                        )
                    )
                    payment.add(
                        Payments(
                            size = it[Payment.size]
                        )
                    )
                }
        }
        return Triple(address, customer, payment)
    }

    fun getTypeSign(): Triple<ArrayList<Customers>, ArrayList<Cities>, ArrayList<SignatureContracts>> {
        val customer : ArrayList<Customers> = arrayListOf()
        val city : ArrayList<Cities> = arrayListOf()
        val signatureContract : ArrayList<SignatureContracts> = arrayListOf()

        transaction {
            (Customer.leftJoin(Address).leftJoin(City).leftJoin(Participants).leftJoin(Contract).leftJoin(Signature_Contract)
                    ).slice(Customer.FIO, City.name, Signature_Contract.typeSign)
                .select { Signature_Contract.typeSign.eq("digital") and
                        Customer.FIO.eq("Borisov") }.forEach() {
                    customer.add(
                        Customers(
                            FIO = it[Customer.FIO]
                        )
                    )
                    city.add(
                        Cities(
                            name = it[City.name]
                        )
                    )
                    signatureContract.add(
                        SignatureContracts(
                            typeSign = it[Signature_Contract.typeSign]
                        )
                    )
                }
        }
        return Triple(customer,city,signatureContract)
    }

    fun getMoscowCust(): Triple<ArrayList<Employers>, ArrayList<Positions>, ArrayList<SignatureContracts>> {
        val employer : ArrayList<Employers> = arrayListOf()
        val position : ArrayList<Positions> = arrayListOf()
        val signatureContract : ArrayList<SignatureContracts> = arrayListOf()

        transaction {
            (Employer.leftJoin(Signature_Contract).leftJoin(Position)
                    ).slice(Employer.name, Position.post, Signature_Contract.dateSign)
                .select {Signature_Contract.dateSign.between(DateTime("2020-8-10"),DateTime("2020-12-10")) }.forEach() {
                    employer.add(
                        Employers(
                            name = it[Employer.name]
                        )
                    )
                    position.add(
                        Positions(
                            post = it[Position.post]
                        )
                    )
                    signatureContract.add(
                        SignatureContracts(
                            dateSign = it[Signature_Contract.dateSign].toString("dd-MM-yyyy")
                        )
                    )
                }
        }
        return Triple(employer, position, signatureContract)
    }

}
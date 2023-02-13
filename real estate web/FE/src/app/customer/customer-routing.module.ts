import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {CustomerCreateComponent} from './customer-create/customer-create.component';
import {AuthGuard} from '../authGuard/auth.guard';
import {AdminGuard} from '../authGuard/admin.guard';
import {CustomerGuard} from '../authGuard/customer.guard';
import {EmployeeGuard} from '../authGuard/employee.guard';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerDetailComponent} from './customer-detail/customer-detail.component';
import {CustomerAddComponent} from './customer-add/customer-add.component';
import {PostListCustomerComponent} from '../post/post-list-customer/post-list-customer.component';
import {AdminEmployeeGuard} from "../authGuard/admin-employee.guard";

const routes: Routes = [
  {
    path: '', component: CustomerListComponent,
    canActivate: [AuthGuard] && [AdminEmployeeGuard]
  },
  {
    path: 'create', component: CustomerCreateComponent
  },
  {
    path: 'add', component: CustomerAddComponent,
    canActivate: [AuthGuard] && [AdminEmployeeGuard]
  },
  {
    path: 'edit/:idCustomer', component: CustomerEditComponent,
    canActivate: [AuthGuard] && [AdminEmployeeGuard]
  },
  {
    path: 'detail/:idCustomer', component: CustomerDetailComponent, canActivate: [AuthGuard] && [AdminEmployeeGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule {
}

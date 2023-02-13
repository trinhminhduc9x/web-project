import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SecurityRoutingModule } from './security-routing.module';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { LoginModalComponent } from './login-modal/login-modal.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

@NgModule({
    declarations: [LoginComponent, LoginModalComponent, ChangePasswordComponent],
    exports: [
        LoginModalComponent
    ],
    imports: [
        CommonModule,
        SecurityRoutingModule,
        ReactiveFormsModule
    ]
})
export class SecurityModule { }

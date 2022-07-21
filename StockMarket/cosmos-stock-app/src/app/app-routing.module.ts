import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './controller/admin/admin.component';
import { DashboardComponent } from './controller/dashboard/dashboard.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'admin', component: AdminComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

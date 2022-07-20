import { NgModule } from '@angular/core';

import {MatButtonModule}  from '@angular/material/button';
import {MatCheckboxModule}  from '@angular/material/checkbox';
import {MatToolbarModule}  from '@angular/material/toolbar';
import {MatInputModule}  from '@angular/material/input';
import {MatProgressSpinnerModule}  from '@angular/material/progress-spinner';
import {MatCardModule}  from '@angular/material/card';
import {MatMenuModule}  from '@angular/material/menu';
import {MatIconModule}  from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table'
import {MatSelectModule} from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
@NgModule({
  imports: [MatButtonToggleModule,MatButtonModule,MatCardModule,MatPaginatorModule,MatSortModule,MatTableModule,MatButtonModule,MatSelectModule, MatTabsModule , MatCheckboxModule,MatToolbarModule,MatInputModule,MatProgressSpinnerModule,MatCardModule,MatMenuModule,MatIconModule],
  exports: [MatButtonToggleModule,MatButtonModule,MatCardModule,MatPaginatorModule,MatSortModule,MatTableModule,MatButtonModule,MatSelectModule, MatTabsModule , MatCheckboxModule,MatToolbarModule,MatInputModule,MatProgressSpinnerModule,MatCardModule,MatMenuModule, MatIconModule]
})


export class MaterialModule{}